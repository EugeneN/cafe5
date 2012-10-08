(ns cafe5.ui.cli.cafe)
(use '[cafe5.lib.pessimist :as pessimist]
     '[cafe5.core]
     '[cafe5.ui.cli.logo :only [the-logo draw-logo]]
     '[cafe5.protocols.feedback :as fb]
     '[cafe5.ui.cli.feedback :as feedback])

(defn- now [] (java.util.Date.))

(def LOG_PREFIX "UI/CLI>")
(def START_TIME (now))

(def exit_hook (fn [] (fb/say nil (str "Cafe was open since " START_TIME))))
(.addShutdownHook (Runtime/getRuntime) (Thread. exit_hook))

(def the-help
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


(defn -main [& argv]
  (if (empty? argv)
    ((fb/murmur nil the-logo)
     (fb/murmur nil the-help))

    (let [args (pessimist/parse-argv argv)
          {{nocolor    :nocolor 
            nologo     :nologo 
            quiet      :shutup
            version    :version
            help       :help
            panic_mode :debug} :global} args]

      (def uifb (feedback/get-fb LOG_PREFIX nocolor quiet panic_mode))

      (if-not nologo (draw-logo uifb))

      (cond
        version (fb/say uifb "Version none")
        help (fb/murmur uifb the-help)
        :else (cafe5.core/go uifb args))

      )))
