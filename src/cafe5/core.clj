(ns cafe5.core)
(use '[cafe5.lib.pessimist :as pessimist])


(defn -main [& argv]
  (if (empty? argv)
    (println "No args")

    (let [args (pessimist/parse-argv argv)]
      (println "Yo" args))))
