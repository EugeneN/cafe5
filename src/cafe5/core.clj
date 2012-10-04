(ns cafe5.core)
(use '[clojure.tools.cli :only [cli]])


(defn -main [& argv] 
  (let [[opts args banner] (cli argv ["-p" "arg1"]
                                     ["--yyy" "arg2"]
                                     ["-h" "--help" "help"])]
    (when (:help opts)
      (println banner)
      (System/exit 0))

    (println opts)
    (println "Yo" argv)))
