(ns ReadEvents)

(def splitted (clojure.string/split-lines (slurp "GenConEvents2015Small.txt")))

(def  ids (splitted 0))

(defn parseLine [line]
   (into {} (map vector (clojure.string/split ids (re-pattern "\t")) (clojure.string/split (splitted line) (re-pattern "\t") ))))

(def eventCount (map inc (range (- (count splitted) 1))))

(def allEvents (map parseLine eventCount))

(defn findByKey [givenKey]
  (into {} (map vector (map (fn [line] (line "Title")) allEvents) (map (fn [line] (line givenKey)) allEvents))))

(defn specificVal [value givenKey]
  (into {} (filter (fn [part] (= value (nth part 1))) (findByKey givenKey))))