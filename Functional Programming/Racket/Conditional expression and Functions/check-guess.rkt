;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-abbr-reader.ss" "lang")((modname check-guess) (read-case-sensitive #t) (teachpacks ((lib "guess.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "guess.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 5.1.2/3 in the book:
;; "HOW TO DESIGN PROGRAMS - An Introduction to Programming and Computing"
;; by M.Felleisen, R.B.Findler, M.Flatt, S.Krishnamurthi

;; This exercise is about conditional expressions, design of
;; programs for problems with diferent conditions

;; Problem 5.1.2: Develop the function check-guess. It consumes two numbers,
;; guess and target. Depending on how guess relates to target, the function
;; produces one of the following three answers: 'TooSmall, 'Perfect, or
;; 'TooLarge.
;; The function implements one part of a two-player number guessing game.
;; One player picks a random number between 0 and 99999. The other player's
;; goal is to determine this number, called target, with the least number of
;; guesses. To each guess, the first player responds with one of the three
;; responses that check-guess implements.
;; The function check-guess and the teachpack guess.ss implement the first
;; player. The teachpack picks the random number, pops up a window in which
;; the second player can choose digits, and hands over the guess and the
;; target to check-guess.

;; check-guess: number numbers -> symbol
;; To check if the guess and target numbers are the same.
;; returns a symbol to tell the user how close was the guess to the target
;; Example: (= (check-guess 200 500) 'TooSmall)
(define (check-guess guess target)
  (cond
    [(< guess target) 'TooSmall]
    [(= guess target) 'Perfect]
    [(> guess target) 'TooLarge];; [else 'TooLarge] also acceptable
    )
  )

;; Tests
(check-expect (check-guess 200 500) 'TooSmall)
(check-expect (check-guess 500 500) 'Perfect)
(check-expect (check-guess 1000 500) 'TooLarge)

;; Function to play the game with a gui interface
(guess-with-gui check-guess)

;; Problem 5.1.3: Develop the function check-guess3. It implements a larger
;; portion of the number guessing game of exercise 5.1.2 than the function
;; check-guess. Now the teachpack hands over the digits that the user
;; guesses, not the number that they form.
;; To simplify the problem a little bit, the game works with 3 numbers.
;; Thus, check-guess3 comsumes 3 digits and a number. The first digit is
;; the least significant, the third one is the most significant. The number
;; is called target and represents the randomly chosen number.
;; Depending on how guess, the number determined by the 3 digits relates to
;; target, check-guess3 produces one of the following three answers:
;; 'TooSmall, 'Perfect, or 'TooLarge.

;; check-guess3: number number number number -> symbol
;; Determine if the guess, composed by 3 digits is equal to the randomly
;; chosen number.
;; Example: (check-guess3 1 2 1 123) returns 'TooSmall.
(define (check-guess3 a b c guess)
  (cond
    [(< (convert3 a b c) guess) 'TooSmall]
    [(= (convert3 a b c) guess) 'Perfect]
    [(> (convert3 a b c) guess) 'TooLarge]
   )
  )

; Tests
(check-expect (check-guess3 1 2 1 123) 'TooSmall)
(check-expect (check-guess3 2 7 1 123) 'TooLarge)
(check-expect (check-guess3 3 2 1 123) 'Perfect)

;; Function to play the game with a gui interface
(guess-with-gui-3 check-guess3)

;; previously developed function to convert 3 digits in a whole number,
;; copied to promote reusability.

;; convert3: number number number -> number
;; Takes three digits and returns a number 
;; with the order of the digits reversed.
;; Example: (= (convert3 1 2 3) 321)
(define (convert3 a b c)
  (+ (* c 100) (* b 10) a))