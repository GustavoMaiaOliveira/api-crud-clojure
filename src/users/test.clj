(ns users.test)

;(def teste {:id 14, :name "Nina", :age 21})
;
;(def teste2 {:id 15, :name "Singer", :age 30})
;
;(assoc teste :age 30)
;
;;(print(dissoc teste :id))
;;
;;(type (read-string "15"))
;;
;;(-> teste
;;    (dissoc :age))
;
;(if (contains? teste :id)
;  (println "vdd")
;  (println "mentira"))

(def unmentionables #{"YHWH"
                      "Voldemort"
                      "Mxyzptlk"
                      "Rumplestiltskin"
                      "曹操"})

(defn ok [message]
  {:status 200 :body message})

(defn not-found []
  {:status 404 :body "Not found\n"})

(defn greeting-for [greet-name]
  (cond
    (unmentionables greet-name) nil
    (empty? greet-name) "Hello, world!\n"
    :else (str "Hello, " greet-name "\n")))

(defn greet-handler [request]
  (let [greet-name (get-in request [:query-params :name])
        message    (greeting-for greet-name)]
    (if message
      (ok message)
      (not-found))))

(def my-string "This string contains a \"double quote\" character.")
(println my-string)