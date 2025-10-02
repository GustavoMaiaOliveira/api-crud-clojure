(ns helpers.model-for-users
  (:require [schema.core :as s]))

(s/defschema user-model
  {:id s/Num
   :user s/Str
   :age s/Num})

(def new-user
  {
   :id 11,
   :user "Kevin",
   :age 32
   })

;(when (s/validate user-model new-user) true (println "I'm passing her"))

(s/check user-model new-user)

;(if (= (s/check user-model new-user) nil)
;  (println "Dado correto!")
;  (println "Dado incorreto!"))

(if (= (s/check user-model new-user) nil)
  (println "Dado correto!")
  (println "Dado incorreto!"))

;(println "I'm passing her")

;(s/validate (s/maybe s/Keyword) :a)
;(s/validate (s/maybe s/Keyword) nil)
;
;(println "receba")
;
;(s/validate
;  {:some-var3
;   (s/conditional
;     #(= "string" (get-in % ["mytype1" "type"]))
;     {s/Str s/Any}
;     )}
;  {:some-var3 {"mytype1" {"type" "string"}}})
;
;(if (malli.core/validate :int 1) :yep :nope)
;
;()