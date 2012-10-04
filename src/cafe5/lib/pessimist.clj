(ns cafe5.lib.pessimist)
(use '[clojure.string :only (join split)])

(def GLOBAL 'global')

(defn- f [arg]
  (cond
    (= (.substring arg 0 2) "--") (clojure.string/split arg #"=")
    (= (.substring arg 0 1) "-") [arg, true]
    :else arg))


(defn- fsm []
  (def s GLOBAL)

  (fn [i]
    (if (= (.substring i 0 1) "-")
      [s i]
      ((set! s i)
       [s nil]))))

(defn get-args-list [argv]
  (into {} (for [[k v] argv] [k v])))
