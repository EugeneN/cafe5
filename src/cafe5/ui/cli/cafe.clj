(ns cafe5.ui.cli.cafe)
(use '[cafe5.lib.pessimist :as pessimist]
     '[cafe5.core]
     '[cafe5.ui.cli.logo :only [show-logo]]
     '[cafe5.protocols.feedback]
     '[cafe5.ui.cli.feedback])

(def LOG_PREFIX "UI/CLI>")
(def START_TIME (System/currentTimeMillis))

(defn- get-time-since [start-time]
  (/ (- (System/currentTimeMillis) start-time) 1000.))

(def default-fb (get-fb LOG_PREFIX false false false))

(def exit_hook
  (fn [] (say default-fb (str "Cafe was open <" (get-time-since START_TIME ) ">s."))))
(.addShutdownHook (Runtime/getRuntime) (Thread. exit_hook))


(defn -main [& argv]
  (let [args (pessimist/parse-argv argv)
        {{nocolor    :nocolor
          nologo     :nologo
          quiet      :shutup
          panic_mode :debug} :global} args]

    ;; Colors, loudness etc are UI businesses
    (def uifb (get-fb LOG_PREFIX nocolor quiet panic_mode))

    ;; Logo is a UI's business too
    (if-not nologo (show-logo uifb))

    (cafe5.core/go uifb args)))
