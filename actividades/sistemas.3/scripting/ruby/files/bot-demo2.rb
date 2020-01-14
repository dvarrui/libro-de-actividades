#!/usr/bin/env ruby

require 'telegram/bot'

# token:
# 1. Write your TOKEN value into "token" variable or
# 2. Create a local file "hiden.token" with your TOKEN value inside
token = `cat hide.token`.strip
bot_username = '@dvarrui_bot'

puts "[INFO] Running bot #{$0}..."

Telegram::Bot::Client.run(token) do |bot|
  bot.listen do |message|
    puts " => #{message.text}"
    hello = Regexp.new("/hello|hello#{bot_username}")
    byebye = Regexp.new("/byebye|byebye#{bot_username}")
    case message.text
    when hello
      bot.api.send_message(chat_id: message.chat.id, text: "Hello Word!")
    when byebye
      bot.api.send_message(chat_id: message.chat.id, text: "Bye bye!")
      exit
    end
  end
end
