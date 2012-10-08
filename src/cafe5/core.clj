(ns cafe5.core)
(use '[cafe5.lib.pessimist :as pessimist]
     '[cafe5.protocols.feedback :as fb])

(defn go [uifb args]
  (fb/say uifb ["Yo there"])
  (fb/shout uifb ["Yo" args])
  )
