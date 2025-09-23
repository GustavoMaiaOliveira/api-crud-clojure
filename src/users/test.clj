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

(def n {:name})

(contains? n
           teste)