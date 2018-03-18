;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname ticketprofit) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 2.3.3 + 3.1.1/2 in the book:
;; "HOW TO DESIGN PROGRAMS - An Introduction to Programming and Computing"

;; This exercise is about basic extraction of data from a textual
;; described problem, interdepencies in data and auxiliary helping
;; functions.

;; Problem 2.3.3: An old-style movie theater has a simple profit function.
;; Each costumer pays 5$ per ticket. Every performance costs the theater
;; 20$, plus 0.50$ per attendee. Develop the function total-profit. It
;; consumes the number of attendees (of a show) and produces how much
;; income the attendees produce.

;; total-profit: number -> number
;; Calculates the profit of a movie theater in relation to the
;; attendance, with fixed values for ticket price and cost per attendee
;; and show.
;; Example: (= (total-profit 50) 205)
(define (total-profit attendees)
  (- (* attendees 5.00) (+ 20 (* attendees 0.5))))

;; Test
(check-within (total-profit 50) 205 0.001)
(check-within (total-profit 0) -20 0.001)
(check-within (total-profit 125) 542.5 0.001)

;; Problem 3.1.1/2: Imagine the owner of a movie theater who has complete
;; freedoom  in setting ticket prices. The more he charges, the fewer the
;; people who can afford tickets. In a recent experiment the owner
;; determined a precise relationship between the price of a ticket and
;; average attendance. At a price of 5.00$ per ticket, 120 people attend
;; a performance. Decreasing the price by a dime (0.10$) increases
;; attendance by 15. Unfortunately, the increased attendance also comes
;; at an increased cost. Every performance costs the owner 180$. Each
;; attendee costs another four cents (0.04$). The owner would like to
;; know tthe exact relationship between profit and ticket price so that
;; he can determine the price at which he can make the highest profit.


;; real-profit: number -> number
;; Calculates the profit of a movie theater in relation to the
;; price defined by the owner.
;; Example: (= (real-profit 4.90) 476.1)
(define (real-profit price) (- (earnings price) (losses price)))

;; Tests
(check-within (real-profit 4.90) 476.1 0.001)
(check-within (real-profit 5.00) 415.2 0.001)
(check-within (real-profit 4.00) 889.2 0.001)
(check-within (real-profit 3.00) 1063.2 0.001)

;; earnings: number -> number
;; Calculates the earnings of a movie theater in relation to the
;; price defined by the owner.
;; Example: (= (earnings 4.90) 661.5)
(define (earnings price) (* price (attendees price)))

;; Tests
(check-within (earnings 4.90) 661.5 0.001)
(check-within (earnings 5.00) 600 0.001)
(check-within (earnings 4.00) 1080 0.001)
(check-within (earnings 3.00) 1260 0.001)

;; loss-calc: number -> number
;; Abstraction over the constants of the calculation of losses related to
;; the number of attendees.
;; Example: (= (loss-calc 135) 185.4)
(define (loss-calc attendees-price) (+ 180 (* attendees-price 0.04)))

;; Tests
(check-within (loss-calc 135) 185.4 0.001)
(check-within (loss-calc 120) 184.8 0.001)
(check-within (loss-calc 270) 190.8 0.001)
(check-within (loss-calc 420) 196.8 0.001)

;; losses: number -> number
;; Calculation of the losses in relation to the defined price, using two
;; auxiliary functions.
;; Example: (= (losses 4.90) 185.4)
(define (losses price) (loss-calc (attendees price)))

;; Tests
(check-within (losses 4.90) 185.4 0.001)
(check-within (losses 5.00) 184.8 0.001)
(check-within (losses 4.00) 190.8 0.001)
(check-within (losses 3.00) 196.8 0.001)

;; attendees: number -> number
;; Calculates the number of attendes in a show according to the defined
;; price. The lowest abstraction point of the exercises.
;; Example: (= (attendees 4.90) 135)
(define (attendees price) (+ (* (- 5.00 price) 150) 120))

;; Tests
(check-expect (attendees 4.90) 135)
(check-expect (attendees 5.00) 120)
(check-expect (attendees 4.00) 270)
(check-expect (attendees 3.00) 420)

