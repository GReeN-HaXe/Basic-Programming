;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname Hangman) (read-case-sensitive #t) (teachpacks ((lib "hangman.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hangman.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 6.7 in the book:
;; " HOW TO DESIGN PROGRAMS - An Introduction to Programming and Computing "

;; This exercises is part 2 of the introduction of structures in the book
;; It gives insight on structures, the constructors and the selectors it
;; creates.
;; It also allows a first touch with iterative refinement of programs.

(start 300 300) ;; Creates the canvas to draw the pictures
(draw-solid-line (make-posn 0 20) (make-posn 150 20) 'black) 
;; Horizontal wooden stick that hold the hangman.
(draw-solid-line (make-posn 150 20) (make-posn 150 40) 'black)
;; Vertical part of the stick that holds the noose.
(draw-solid-rect (make-posn 75 260) 150 40 'black)
;; Bottom step

;; Problem 6.7.1: Develop the function draw-next-part, which draws pieces of a
;; hangman figure. The function consumes one of the seven symbols: 'right-leg
;; 'left-leg 'left-arm 'right-arm 'body 'head 'noose. It always returns true
;; and draws the matching parts of the figure.

;; draw-next-part: symbol -> boolean
;; Takes a symbol and draws the hangman body part described by the symbol in a
;; canvas.
;; Example: (= (draw-next-part 'head) true)
(define (draw-next-part a-part)
  (cond
    [(symbol=? a-part 'head) (and (draw-circle (make-posn 175 60) 30 'black)
                                  ;; Lines to draw the eyes
                                  (draw-solid-line (make-posn 170 45)
                                                   (make-posn 180 55) 'black)
                                  (draw-solid-line (make-posn 180 45)
                                                   (make-posn 170 55) 'black)
                                  (draw-solid-line (make-posn 185 50)
                                                   (make-posn 195 60) 'black)
                                  (draw-solid-line (make-posn 195 50)
                                                   (make-posn 185 60) 'black))]
    [(symbol=? a-part 'body) (draw-solid-line (make-posn 148 70)
                                              (make-posn 148 160) 'black)]
    [(symbol=? a-part 'left-arm) (draw-solid-line (make-posn 148 85)
                                                  (make-posn 135 120) 'black)]
    [(symbol=? a-part 'right-arm) (draw-solid-line (make-posn 148 85)
                                                   (make-posn 160 120) 'black)]
    [(symbol=? a-part 'left-leg) (draw-solid-line (make-posn 148 150)
                                                  (make-posn 135 195) 'black)]
    [(symbol=? a-part 'right-leg) (draw-solid-line (make-posn 148 150)
                                                   (make-posn 160 195) 'black)]
    [(symbol=? a-part 'noose) (draw-circle (make-posn 150 60) 20 'black)]))


;; Problem 6.7.2: Provide a structure definition and a data definition for
;; representing three-letter words.

(define-struct word (a b c))
;; a word is a structure:
;                    (make-word a b c)
;; where a, b and c are symbols representing letters.

;; Problem 6.7.3: Develop a function reveal. It consumes three arguments:
;; 1. the chosen word, which is the word that we have to guess;
;; 2. the status word, which shows which portion of the word has been revealed
;; so far;and
;; 3. a letter, which is our current guess.
;; The function produces a new status word, that is, a word that contains
;; ordinary letters and '_. The fields in the new status word are determined
;; by comparing the guess with each pair of letter from the status word and
;; the chosen word.

;; reveal: word word symbol -> word
;; The function takes a secret word composed of three letter which are to be
;; guessed, a status word which determines the status of the guessed letters
;; and a symbol which is the guess
;; Example: (= (reveal (make-word 't 'e 'a) (make-word '_ 'e '_) 't)
;;             (make-word 't 'e '_)
(define (reveal chosen-word status-word letter)
  (cond
    [(symbol=? letter (word-a chosen-word)) (if-guess-first letter
                                                            chosen-word
                                                            status-word)]
    [(symbol=? letter (word-b chosen-word)) (if-guess-second letter
                                                            chosen-word
                                                            status-word)]
    [(symbol=? letter (word-c chosen-word)) (make-word (word-a status-word)
                                                       (word-b status-word)
                                                       letter)]
    [else status-word]))

;; if-guess-first: symbol word word -> word
;; Auxiliary function created for when the first letter is true, it checks
;; the remaining letters in case the chosen word has repeated solutions
;; Example: (= (if-guess-first 'l (make-word 'l 'o 'l) (make-word '_ '_ '_) 'l)
;;             (make-word 'l '_ 'l))
(define (if-guess-first letter chosen status)
  (cond
    [(symbol=? letter (word-b chosen)) (make-word letter letter
                                           (word-c status))]
    [(symbol=? letter (word-c chosen)) (make-word letter letter letter)]
    [else (make-word letter (word-b status)
                                           (word-c status))]))

;; if-guess-second: symbol word word -> word
;; Auxiliary function created for when the second letter is true, it checks
;; the remaining letter in case the chosen word has repeated solutions
;; Example: (= (if-guess-second 'l (make-word 'a 'l 'l) (make-word '_ '_ '_) 'l)
;;             (make-word '_ 'l 'l))
(define (if-guess-second letter chosen status)
  (cond
    [(symbol=? letter (word-c chosen)) (make-word (word-a status)
                                                  letter letter)]
    [else (make-word (word-a status) letter (word-c status))]))

;; Tests
(check-expect (reveal (make-word 't 'e 'a) (make-word '_ 'e '_) 'u)
              (make-word '_ 'e '_))
(check-expect (reveal (make-word 'a 'l 'e) (make-word 'a '_ '_) 'e)
              (make-word 'a '_ 'e))
(check-expect (reveal (make-word 'a 'l 'l) (make-word '_ '_ '_) 'l)
              (make-word '_ 'l 'l))

;; Funtion included in the hangman.ss teachpack in racket. Used to test the
;; functions developed in the exercise with an interactive gui.
(hangman make-word reveal draw-next-part)