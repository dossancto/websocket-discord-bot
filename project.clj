(defproject first-webhook-bot "0.1.0-SNAPSHOT"
  :description "FIXME: Add description"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "https://mit-license.org"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [ring/ring-core "1.9.4"]
                 [ring/ring-json "0.5.1"]
                 [http-kit "2.5.3"]
                 [lynxeyes/dotenv "1.1.0"]
                 [com.github.johnnyjayjay/ring-discord-auth "1.0.1"]
                 [org.clojure/tools.logging "1.2.4"]
                 [com.github.johnnyjayjay/slash "0.3.0-SNAPSHOT"]]
  :main ^:skip-aot first-webhook-bot.handler
  :target-path "target/%s"
  :profiles
  {:dev {:dependencies [[com.github.discljord/discljord "1.3.1"]]}
   :uberjar {:global-vars {*warn-on-reflection* true}
             :aot :all
             :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
