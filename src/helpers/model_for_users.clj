(ns helpers.model-for-users
  (:require [schema.core :as s]))

;(s/defschema user-model-keywords
;  {:id s/Num
;   :user s/Str
;   :age s/Num})
;
;(def new-user-keywords
;  {
;   :id 11,
;   :user "Kevin",
;   :age 32
;   })
;
;(s/check user-model-keywords new-user-keywords)
;
;(if (= (s/check user-model-keywords new-user-keywords) nil)
;  (println "Dado correto!")
;  (println "Dado incorreto!"))
;
;(s/check user-model-keywords new-user-keywords)
;
;(if (= (s/check user-model-keywords new-user-keywords) nil)
;  (println "Dado correto!")
;  (println "Dado incorreto!"))

;(s/defschema user-model-keyword-age
;  {:age s/Num})
;
;(def new-user-keyword-age
;  {:age 32})
;
;(if (= (s/check user-model-keyword-age new-user-keyword-age) nil)
;  (println "Dado correto!")
;  (println "Dado incorreto!"))