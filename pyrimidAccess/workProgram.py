import re
from dataclasses import dataclass, field

@dataclass(order=True)
class Word():
    sort_index: int = field(init=False, repr=False)
    key: int = field(init=True, repr=True)
    word: str
    
    def __post_init__(self):
        self.sort_index = self.key

def word_table_from_file(file_name:str) -> list[str] :
    """_summary_

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