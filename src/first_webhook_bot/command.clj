(ns first-webhook-bot.command
  (:require
   [discljord.messaging :as dm]
   [dotenv :refer [env]]
   [first-webhook-bot.commands.hello :refer :all]
   [slash.command :refer [defpaths]]))

(def discord-token (env "DISCORD_TOKEN"))
(def guild-id (env "GUILD_ID"))

(def conn (dm/start-connection! discord-token))

;; All the commands that the bot will response
(def commands (into [] (concat all-hello-commands)))

;; Add the slash commands to the channel.
@(dm/bulk-overwrite-guild-application-commands! conn (:id @(dm/get-current-user! conn)) guild-id commands)

(defpaths command-paths
  #'chat-handler
  #'greet-handler)
