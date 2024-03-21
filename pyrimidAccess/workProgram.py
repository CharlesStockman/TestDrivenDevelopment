import re
from dataclasses import dataclass, field

@dataclass(order=True)
class Word():
    sort_index: int = field(init=False, repr=False)
    key: int = field(init=True, repr=True)
    word: str
    
    def __post_init__(self):
        self.sort_index = self.key

def word_table_from_file_sorted_by_id(file_name:str) -> list[str] :
    """_summary_
    
        Crate a list of the index and word pairs.

    Args:
        file_name (str): _description_  The file name that will contains the primary key and word for each row in the table

    Returns:
        list[str]: _description_    A sorted list ( sorted by key ) of rows with attributes key and word.
    """    
    
    results = []
    with open(file_name, "r") as file:
        for line in file:
            split_string = re.split(r"\s+",line )
            results.append( Word(int(split_string[0]), split_string[1]))
    
    results.sort()
    return results

def create_list_with_right_indexs(length_of_word_database:int) -> list[int]:
    """_summary_

    Args:
        length_of_word_database (int): _description_    The number of elements the pyriamd has total

    Returns:
        list[int]: _description_    A list of all the far right elements in the pyriamnd 
    """    
    index = 1;
    total = 0;
    indexes = [] 
    
    while ( total < length_of_word_database):
        total = total + index
        indexes.append(total)
        
        index = index + 1       
        
    indexes = [ value - 1 for value in indexes ]

    return indexes