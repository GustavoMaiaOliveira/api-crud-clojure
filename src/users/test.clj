(ns users.test)

(def teste {:id 14, :name "Nina", :age 21})

(print(dissoc teste :id))

(type (read-string "15"))