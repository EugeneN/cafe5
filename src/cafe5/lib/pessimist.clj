
;;; Converts a list like this:
;;; ["--debug" "--zzz"
;;;  "menu" "--new=zzzz"
;;;  "build" "--app_root=xxx" "-f" "--formula=zzz"]
;;;
;;; to this:
;;; {:build {:formula "zzz", :f true, :app_root "xxx", :build true},
;;;  :menu {:new "zzzz", :menu true},
;;;  :global {:zzz true, :debug true}}

(ns cafe5.lib.pessimist)
(require '[clojure.string :as str])

(def GLOBAL :global)

(defn- split-arg [a] (str/split a #"="))

(defn- is-arg [a] (= (.substring a 0 1) "-"))

(def is-cmd (complement is-arg))

(defn- arg2key [a]
  (keyword (str/replace (first (split-arg a)) #"^-*" "")))

(defn- format-value [a]
  (let [arg-value (second (split-arg a))]
    (if (nil? arg-value) true arg-value)))

(defn- arg2value [cur-arg new-key]
  (if (= cur-arg new-key) {} (format-value cur-arg)))

(defn- do-parse [a-list a-map a-key]
  (let [cur-arg (first a-list)
        new-list (rest a-list)
        new-key (if (is-cmd cur-arg) (keyword cur-arg) a-key)
        new-map (assoc a-map
                       new-key
                       (if (= (keyword cur-arg) new-key)
                         {}
                         (assoc (or (a-map new-key) {})
                           (arg2key cur-arg)
                           (arg2value cur-arg new-key))))]

    (if (empty? new-list)
      new-map
      (do-parse new-list new-map new-key))))

(defn parse-argv [argv]
  (if (empty? argv)
    (sorted-map)
    (do-parse argv (sorted-map) GLOBAL)))
