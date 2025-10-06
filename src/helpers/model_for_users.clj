(ns helpers.model-for-users
  (:require [schema.core :as s]))

(s/defschema user-model-keywords
  {:id s/Num
   :user s/Str
   :age s/Num})

(def new-user-keywords
  {:id 10, :name "Julia", :age 26})

(s/check user-model-keywords new-user-keywords)

(if (= (s/check user-model-keywords new-user-keywords) nil)
  (println "Dado correto 1!")
  (println "Dado incorreto 1!"))

(s/check user-model-keywords new-user-keywords)

(if (= (s/check user-model-keywords new-user-keywords) nil)
  (println "Dado correto 2!")
  (println "Dado incorreto 2!"))

(s/defschema schema-keyword-age
  {:age s/Num})

(def new-user-keyword-age
  {:age 85})

(if (= (s/check schema-keyword-age new-user-keyword-age) nil)
  (println "Dado correto 3!")
  (println "Dado incorreto 3!"))

;(s/validate s/Num "10")
;
;(println "teste")

;(def SendMessage
;  {(s/optional-key :owner)    s/Str
;   (s/optional-key :title)    s/Str
;   (s/optional-key :message)  s/Str
;   (s/optional-key :tag)      s/Any
;   (s/optional-key :app)      s/Any
;   (s/optional-key :channels) [s/Str]
;   (s/optional-key :emails)   [s/Str]})

