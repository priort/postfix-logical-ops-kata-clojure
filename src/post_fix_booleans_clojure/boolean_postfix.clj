(ns post-fix-booleans-clojure.boolean-postfix)

(defn ^:private push-true-val-to-stack [stack] (conj stack true))

(defn ^:private push-false-val-to-stack [stack] (conj stack false))

(defn ^:private postfix-or [[b1 b2 & t]] (conj t (or b1 b2)))

(defn ^:private postfix-and [[b1 b2 & t]] (conj t (and b1 b2)))

(defn ^:private postfix-x-or [[b1 b2 & t]] (conj t (or (and b1 (not b2)) (and b2 (not b1)))))

(defn ^:private postfix-not [[b1 & t]] (conj t (not b1)))

(defn expr-str->functions-on-stack [postfix-expression-str]
  (let [syntax-map {:1 push-true-val-to-stack
                    :0 push-false-val-to-stack
                    :R postfix-or
                    :A postfix-and
                    :X postfix-x-or
                    :N postfix-not
                    }]
    (map #((keyword (str %)) syntax-map) (filter #(not= % \space) postfix-expression-str))))

(defn eval [postfix-expression-str]
  (first (reduce #(%2 %1) (list) (expr-str->functions-on-stack postfix-expression-str))))




