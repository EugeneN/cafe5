(ns cafe5.ui.cli.cafe)
(use '[cafe5.lib.pessimist :as pessimist])

(defn- now [] (java.util.Date.))

(def LOG_PREFIX "UI/CLI")
(def START_TIME (now))

(defn- exit_hook []
  (println "Cafe was open" (- (now) START_TIME))
  (System.exit 0))

(.addShutdownHook (Runtime/getRuntime)
  (Thread. exit_hook))

(defn -main [& argv]
  (if (empty? argv)
    (println "No args")

    (let [args (pessimist/parse-argv argv)]
      (println "Yo" args))))
