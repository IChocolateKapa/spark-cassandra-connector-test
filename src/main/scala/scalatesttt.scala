package scalatest

import com.datastax.spark.connector._

import com.datastax.spark.connector.streaming._

object Testobj extends App {

  def isPalindrome(word: String) = {
    val modifiedWord = word.toLowerCase.replaceAll("[^a-z0-9]", "")
    val reversed = modifiedWord.reverse()
    modifiedWord == reversed
  }

  println("Is 'Herculaneum' a palindrome? " + isPalindrome("Herculaneum"))
}
