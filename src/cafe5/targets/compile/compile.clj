(ns cafe5.targets.compile.compile
  "Compile target"
  (:use cafe5.protocols.feedback
       cafe5.protocols.target
       [clojure.java.io :only [file]]
       [bultitude.core :only [namespaces-on-classpath]]
       [cafe5.protocols.adaptor :as PAdaptor]))

(def ^{:private true} ADAPTORS-PATH "../../adaptors")
(def ^{:private true} ADAPTOR-FACTORY-FN "get-adaptor")

(defn- get-adaptors [& dirs]
  (let [classpaths (namespaces-on-classpath :classpath (map file dirs))]
    (println classpaths)
    (doseq [f classpaths]
      (require f))
    (map #(ns-resolve % ADAPTOR-FACTORY-FN ) classpaths)))

(defn- target-worker [ctx]
  (let [adaptors (get-adaptors ADAPTORS-PATH)
        the-adaptor (filter #(PAdaptor/match % ctx) adaptors)]
    (PAdaptor/hello the-adaptor "Yo there")))

(defn get-target []
  (reify ITarget
    (run [target ctx] (target-worker ctx))))
