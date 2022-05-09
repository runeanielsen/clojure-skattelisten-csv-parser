(ns skattelisten-csv-parser.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.data.json :as json])
  (:gen-class))

(defn make-company
  [splitted-line]
  {:csv (splitted-line 0)
   :name (splitted-line 1)
   :se (splitted-line 2)
   :income-year (splitted-line 3)
   :company-type (splitted-line 5)
   :taxable-income (splitted-line 8)
   :deficit (splitted-line 9)
   :corporate-tax (splitted-line 10)})

(defn csv-line->json-newline [line]
  (-> line
      (str/split #";")
      make-company
      (json/write-str :escape-unicode false)
      (str "\n")))

(defn process-file [input-path output-path]
  (with-open [reader (io/reader input-path :buffer-size 4096)
              writer (io/writer output-path :buffer-size 4096)]
    (doseq [line (drop 1 (line-seq reader))]
      (.write writer (csv-line->json-newline line)))))

(defn -main [& args]
  (let [[input-path output-path] args]
    (process-file input-path output-path)))
