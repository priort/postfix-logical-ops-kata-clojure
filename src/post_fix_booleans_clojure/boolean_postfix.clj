(ns post-fix-booleans-clojure.boolean-postfix)

(defn push-true-val-to-stack [stack] (conj stack true))

(defn push-false-val-to-stack [stack] (conj stack false))

(defn postfix-or [stack]
  (conj (rest (rest stack)) (or (first stack) (first (rest stack)))))

(defn postfix-and [stack]
  (conj (rest (rest stack)) (and (first stack) (first (rest stack)))))

(defn postfix-x-or [stack]
  (let [b1 (first stack)
        b2 (first (rest stack))]
    (conj (rest (rest stack)) (or (and b1 (not b2)) (and b2 (not b1))))))

(defn postfix-not [stack] (conj (rest stack) (not (first stack))))

(defn stack-unchanged [stack] (println (str "stack unchanged") stack) stack)

(defn expr-str->functions-on-stack [postfix-expression-str]
  (let [syntax-map {:1       push-true-val-to-stack
                    :0       push-false-val-to-stack
                    :R       postfix-or
                    :A       postfix-and
                    :X       postfix-x-or
                    :N       postfix-not
                    :default stack-unchanged}
        post-fix-expr-minus-spaces-and-num-tokens-char (filter #(not= % \space) (rest postfix-expression-str))]
    (map #((keyword (str %)) syntax-map) post-fix-expr-minus-spaces-and-num-tokens-char)))

(defn eval [postfix-expression-str]
  (first (reduce #(%2 %1) (list) (expr-str->functions-on-stack postfix-expression-str))))

