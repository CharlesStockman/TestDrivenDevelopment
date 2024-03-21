import unittest
import workProgram
from dataclasses import dataclass

@dataclass
class Word():
    key: int
    word: str

class TestProgram(unittest.TestCase):

    
   def test_word_table_from_file_sorted_by_id(self):

    result_list = []
    
    result_list.append(Word(1, "I"))
    result_list.append(Word(2, "dogs"))
    result_list.append(Word(3, "love"))
    result_list.append(Word(4, "cats"))
    result_list.append(Word(5, "you"))
    result_list.append(Word(6, "computers"))
        
    actual_list = workProgram.word_table_from_file_sorted_by_id("data.txt")
    
    for index in range(0,6):
        self.assertTrue(actual_list[index].key == result_list[index].key)
        self.assertTrue(actual_list[index].word == result_list[index].word)     
        
   def test_create_list_with_index_of_right_indexes(self):

    length_of_word_database = 10;
    result_index_list = [0, 2, 5, 9]
    
    actual_index_list = workProgram.create_list_with_right_indexs(length_of_word_database)
    self.assertListEqual(result_index_list, actual_index_list)
                
if ( __name__ == "__main__"):
    unittest.main()
    

 
    
    
