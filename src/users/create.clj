(ns users.create
  (:require [clojure.java.io :as io]
            [cheshire.core :as json]
            [helpers.file-path :as file-path]
            [helpers.model-for-users :as model]
            [schema.core :as s]))

(defn create-new-user [request]
  (let [new-user (:json-params request)
        existing-users (if (.exists (io/file file-path/file-path))
                         (json/parse-string (slurp file-path/file-path) true)
                         [])
        updated-users (conj existing-users new-user)]

    (if (= (s/check model/skeleton-user-model new-user) nil)
      (do (spit file-path/file-path (json/generate-string updated-users {:pretty true})) {:status  200
                                                                                          :body {:message "Usuário criado com sucesso!" :user new-user}})
      {:status  400
       :body    {:message (str "Falha ao criar o usuário. Verifique se os campos seguem o seguinte padrão: {“id” : 123, “name” : “João”, “age” : 30}")}})))

    ;(spit file-path/file-path (json/generate-string updated-users {:pretty true}))
    ;{:status 201
    ; :headers {"Content-Type" "application/json"}
    ; :body (json/generate-string {:message "Usuário criado com sucesso!"
    ;                              :user new-user})}))

