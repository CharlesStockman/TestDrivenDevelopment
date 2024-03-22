import unittest
import workProgram
from dataclasses import dataclass


class TestProgram(unittest.TestCase):
    
    def test_drawpyramid(self):
    
      print("build pyramid")
    
      numberPerLine = 1
      total = 1
    
      while(total <= 300 ):
        for counter in range(numberPerLine):
            print(total, " ", end="")
            total = total + 1
        print("\n")
        numberPerLine = numberPerLine + 1

    
    def test_word_table_from_file_sorted_by_id(self):
    
        result_map = {}
        result_map[1] = "I"
        result_map[2] = "dogs"
        result_map[3] = "love"
        result_map[4] =  "cats"
        result_map[5] = "you"
        result_map[6] = "computers"
        
        actual_map = workProgram.word_table_from_file_sorted_by_id("data.txt")
        self.assertDictEqual(actual_map, result_map)
          
    def test_create_list_with_index_of_right_indexes(self):

      length_of_word_database = 10;
      result_index_list = [1, 3, 6, 10]
    
      actual_index_list = workProgram.create_list_with_right_indexs(length_of_word_database)
      self.assertListEqual(result_index_list, actual_index_list)
    
    def test_create_string(self):

      word_map = {}
      word_map[1] = "I"
      word_map[2] = "dogs"
      word_map[3] = "love"
      word_map[4] =  "cats"
      word_map[5] = "you"
      word_map[6] = "computers"
    
      index_list = [1, 3, 6]
    
      result_string = "I love computers"
      actual_string = workProgram.create_sentence(word_map, index_list)
      self.assertEqual(result_string, actual_string)
    
    def do(self):
       filename = "data.txt"
       result_string = "I love computers"
       
       actual_stirng = workProgram.do(filename)
       self.assertEqual(result_string, actual_stirng)


        
       
    
    
if ( __name__ == "__main__"):
    unittest.main()
    

 
    
    
