(ns users.test)

(def teste {:id 14, :name "Nina", :age 21})

(def teste2 {:id 15, :name "Singer", :age 30})

(assoc teste :age 30)

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

(do
  (println "log"
          (+ 1 1)
           (+ 1 1)))
