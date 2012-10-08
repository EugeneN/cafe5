(ns cafe5.core)
(use '[cafe5.lib.pessimist :as pessimist]
     '[cafe5.protocols.feedback]
     '[cafe5.protocols.target])

(def ^{:private true} the-help
  "Cafe is the build system for client side applications (and more).
   It is written in Clojure in functional and concurrent way.

   This is a CLI UI for Cafe v.1

   Parameters:
        -- debug     - include stack traces and other debug info
                       into output

        -- nologo    - exclude logo from output, usefull for sub-commands;

        -- nocolor   - do not use color in output, usefull
                       when directing Cafe's output into a log file;

        -- shutup    - exclude info and warning messages from output.
                       Error and debug messages will be preserved;

        -- version   - returns the current Cafe's version;

        -- help      - this help.

   Sub-commands:
  ")

(defn- show-help [fb] (murmur fb the-help))

(defn- show-version [fb] (say fb "Version none"))

(defn- resolve-seq [args]
  (filter (fn [[k v]] (-> (= k :global) not)) args))

(def ^{:private true} TARGET-NS-PREFIX "cafe5.targets.")
(def ^{:private true} TARGET-FILE-PREFIX "targets/")
(def ^{:private true} TARGET-FILE-EXT ".clj")
(def ^{:private true} TARGET-FACTORY "get-target")

(defn- run-target [proto-ctx [target-kw _]]
  (let [target-name (name target-kw)
        ctx (assoc proto-ctx :own-args
                             ((keyword target-name) (:full-args proto-ctx)))
        target-ns (symbol (str TARGET-NS-PREFIX target-name "." target-name))
        target-factory-fn (symbol TARGET-FACTORY)
        target-file (str TARGET-FILE-PREFIX target-name
                         "/" target-name TARGET-FILE-EXT)]

    (try
      (
       (require target-ns)
       (let [target-factory (ns-resolve target-ns target-factory-fn)
             target (target-factory)]
         (run target ctx)))

      (catch Exception e
        (scream (:fb ctx) ["Error running target" target-name ":" (.getMessage e)])))))

(defn- run-seq [proto-ctx seq]
  (let [run-my-target (partial run-target proto-ctx)]
    (map run-my-target seq)))

(defn go [uifb args]
  (say uifb args)

  (let [{{version :version
          help    :help} :global} args]

    (cond version (show-version uifb)
        help    (show-help uifb)
        :else   (-> (resolve-seq args)
                    ((partial run-seq {:fb uifb :full-args args}))))
  ))
