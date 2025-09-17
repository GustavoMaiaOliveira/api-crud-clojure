(ns users.create
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]
            [helpers.file-path :as file-path]))

(defn create-new-user [request]
  (let [new-user (:json-params request)
        existing-users (if (.exists (io/file file-path/file-path))
                         (json/parse-string (slurp file-path/file-path) true)
                         [])
        updated-users (conj existing-users new-user)]
    (spit file-path/file-path (json/generate-string updated-users {:pretty true}))
    {:status 201
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:message "Usuário criado com sucesso!"
                                  :user new-user})}))