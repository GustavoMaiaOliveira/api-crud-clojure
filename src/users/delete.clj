(ns users.delete
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]))

(def file-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")

(defn delete-user [request]
  (let [params (:json-params request)
        nome (:nome params)
        existing-users (if (.exists (io/file file-path))
                         (json/parse-string (slurp file-path) true)
                          [])
        updated-users (remove #(= (:nome %) nome) existing-users)]
    (spit file-path (json/generate-string updated-users {:pretty true}))
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:message (str "Usuario" nome "deletado com sucesso!")})}))