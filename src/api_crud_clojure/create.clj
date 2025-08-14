(ns api-crud-clojure.create)

(defn create-user
  [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Olá, mundo!"})