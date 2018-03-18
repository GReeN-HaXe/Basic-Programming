;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname Hangman) (read-case-sensitive #t) (teachpacks ((lib "draw.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "draw.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 6.7 in the book:
;; " HOW TO DESIGN PROGRAMS - An Introduction to Programming and Computing "

;; This exercises is part 2 of the introduction of structures in the book
;; It gives insight on structures, the constructors and the selectors it creates.
;; It also allows a first touch with iterative refinement of programs.

;; Problem 6.7.1: Develop the function draw-next-part, which draws pieces of a
;; hangman figure. The function consumes one of the seven symbols: 'right-leg
;; 'left-leg 'left-arm 'right-arm 'body 'head 'noose. It always returns true
;; and draws the matching parts of the figure.

;; draw-next-part: symbol -> boolean
;; Takes a symbol and draw hangman body part described in the symbol
(define (draw-next-part a-part)
  (cond
    [(symbol=? a-part 'head) (and (draw-circle (make-posn 100 100) 50 'black)
                                  (draw-line (make-posn 70 70)
                                             (make-posn 80 80) 'black)
                                  (draw-line (make-posn 80 80)
                                             (make-posn 70 70) 'black)
                                  (draw-line (make-posn 120 70)
                                             (make-posn 130 80) 'black)
                                  (draw-line (make-posn 130 70)
                                             (make-posn 120 80) 'black))]
    [(symbol=? a-part 'body) (draw-line (make-posn 100 150)
                                       (make-posn 100 250) 'black)]
    [(symbol=? a-part 'left-arm) (draw-line (make-posn 100 200)
                                       (make-posn 50 190) 'black)]
    [(symbol=? a-part 'right-arm) (draw-line (make-posn 100 200)
                                       (make-posn 150 200) 'black)]
    [(symbol=? a-part 'left-leg) (draw-line (make-posn 100 250)
                                       (make-posn 50 270) 'black)]
    [(symbol=? a-part 'right-leg) (draw-line (make-posn 100 150)
                                       (make-posn 150 250) 'black)]
    [(symbol=? a-part 'noose) (draw-circle (make-posn 100 150) 20 'black)])))