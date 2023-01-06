(ns first-webhook-bot.handler
  (:require [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring-discord-auth.ring :refer [wrap-authenticate]]
            [ring.util.response :refer [response]]
            [org.httpkit.server :as server]
            [first-webhook-bot.command :refer [command-paths]]
            [clojure.tools.logging :as log]
            [slash.core :as slash]
            [dotenv :refer [env]]
            [slash.webhook :refer [webhook-defaults]]
            [clojure.edn :as edn])
  (:gen-class))

;; Add :message-component and :autocomplete handlers here
(def slash-handlers
  (assoc webhook-defaults
         :application-command #'command-paths))

(def public-key (env "DISCORD_PUBLIC_KEY"))

(defn handler [{interaction :body :as _request}]
  (response (slash/route-interaction slash-handlers interaction)))

(defn start-server
  "Start the server"
  [config]

  (server/run-server
   (-> #'handler
       wrap-json-response
       (wrap-json-body {:keywords? true})
       (wrap-authenticate public-key))
   (:server config)))

(defn -main
  [& _args]
  (log/debug "Reading config...")
  (let [config (edn/read-string (slurp "config/config.edn"))]
    (log/debug "Read config.")
    (log/info "Starting http-kit server")
    (start-server config)
    (log/info (str "Start server at port " (:port (:server config))))))
