import re
from dataclasses import dataclass, field

def word_table_from_file_sorted_by_id(file_name:str) -> list[str] :
    """_summary_
    
        Crate a list of the index and word pairs.

    Args:
        file_name (str): The file name that will contains the primary key and word for each row in the table

    Returns:
        list[str]: A map where the key is the index and the word is the value 
    """    
    
    # Create a list of index,words pairs from the file contents
    results = {}
    with open(file_name, "r") as file:
        for line in file:
            split_string = re.split(r"\s+",line )
            results[int(split_string[0])] = split_string[1]    
    return results

def create_list_with_right_indexs(length_of_word_database:int) -> list[int]:
    """_summary_

    Args:
        length_of_word_database (int): The number of elements in the pyramid total

    Returns:
        list[int]: A list of all last elements of each line of the pyramid
    """    
    index = 1;
    total = 0;
    indexes = [] 
    
    # Get the list of last element in each line of the pyramid as a base 1 index
    while ( total < length_of_word_database):
        total = total + index
        indexes.append(total)
        
        index = index + 1       
    return indexes

def create_sentence(words:list[dataclass], indexes:list[int]) -> str:
    """_summary_

    Args:
        words (list[dataclass]): A map of index/words
        indexes (list[int]): A list of indexes where each index will retrieve a different word from the words map

    Returns:
        str: The sentence the funtion produces.
    """    
    result = [ words[index] + ' ' for index in indexes ]
    result = ''.join(result).strip()
    return result

def do(filename:str) -> str:
    """ A facade pattern that cobines all the the part together to create a sentence.

    Args:
        filename (str): The filename where the index/words tuples are located

    Returns:
        str: The sentecnec created
    """    
    word_table = word_table_from_file_sorted_by_id(filename)
    indexes = create_list_with_right_indexs(len(word_table))
    
    sentence = create_sentence(word_table, create_list_with_right_indexs(len(word_table)))
    return sentence

if __name__ == "__main__":
    print("sentence = ", do("./data2.txt"))
    