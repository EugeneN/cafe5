(ns cafe5.targets.build.build)
(use 'cafe5.protocols.feedback
     'cafe5.protocols.target)


(defn- target-worker [ctx]
  (say (:fb ctx) ["Hello world build" ctx])
  true)

(defn get-target []
  (reify ITarget
    (run [target ctx] (target-worker ctx))))