(ns first-webhook-bot.commands.hello
  (:require [slash.command :refer [defhandler]]
            [slash.command.structure :as cmd]
            [slash.response :as rsp]))

;; Command Names and Options
;; (The declariation slice).

(def greet-command
  (cmd/command
   "greet"
   "Send a greeting."
   :options
   [(cmd/option "user" "The user to greet." :user)]))

(def chat-command
  (cmd/command
   "chat"
   "Kill a chat :)"
   :options
   [(cmd/option "chat" "The chat to kill" :channel)
    (cmd/option "user" "The user that will kill the channel" :user)]))

(def destruct-message-command
  (cmd/command
   "destruct"
   "Show the struct of the message"
   :options

   [(cmd/option "text" "The text to Destruct" :string)]))

;; Command Handlers
;; (Where the command run).

(defhandler greet-handler
  ["greet"]
  _i
  [user_id]

  (-> {:content (str ":wave:" (when user_id (str ", <@" user_id ">")) \!)}
      rsp/channel-message
      rsp/ephemeral))

(defhandler chat-handler
  ["chat"]
  _i
  [_ user_id]

  (-> {:content (str "<@" user_id "> is Killing a chat!")}
      rsp/channel-message
      rsp/ephemeral))

(defhandler destruct-message-handler
  ["destruct"]
  _i
  [message]

  (-> {:content (str message)}
      rsp/channel-message
      rsp/ephemeral))

(def all-hello-commands
  [greet-command chat-command destruct-message-command])
