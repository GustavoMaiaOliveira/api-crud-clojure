(ns users.test)

(def teste {:id 14, :name "Nina", :age 21})

(def teste2 {:id 15, :name "Singer", :age 30})

(assoc teste :age 30)

;(print(dissoc teste :id))
;
;(type (read-string "15"))
;
;(-> teste
;    (dissoc :age))

(defn zapzap
  []
  (when true(contains? teste :ids)
  (->
    (println "1")
    (println "2")
    (println "3")
    (println "4"))
            :else
  (println "n sei")))


(zapzap)
