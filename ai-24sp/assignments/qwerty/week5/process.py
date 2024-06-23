from importlib.metadata import version
from tokenizer import SimpleTokenizerV1
#from splitter import split 

import tiktoken
import torch



with open("../data/dracula.txt", "r") as f:
  raw_text = f.read()
    
import re
#result = re.split(r'([,.:;?_!"()\']|--|\s)', raw_text )
#result = [item.strip() for item in result if item.strip()]

#preprocessed = re.split(r'([,.?_!"()\']|--|\s)', raw_text)
#preprocessed = [item.strip() for item in preprocessed if item.strip()]
 
#all_words = sorted(list(set(preprocessed)))
#vocab_size = len(all_words)

#vocab = {token:integer for integer,token in enumerate(all_words)}
#tokenizer = SimpleTokenizerV1(vocab)

##text = "Hello, do you like tea. Is this-- a test?"

#tokenizer.encode(text)

#tokenizer = SimpleTokenizerV1(vocab)

#text = """"It's the last he painted, you know," Mrs. Gisburn said with pardonable pride."""
#ids = tokenizer.encode(text)
#print(ids)
#print(vocab_size)

#for i, item in enumerate(vocab.items()):
###     break

#for i, item in enumerate(list(vocab.items())[-5:]):
    #print(item)

preprocessed = re.split(r'([,.?_!"()\']|--|\s)', raw_text)
preprocessed = [item.strip() for item in preprocessed if item.strip()]

all_tokens = sorted(list(set(preprocessed)))
all_tokens.extend(["<|endoftext|>", "<|unk|>"])

vocab = {token:integer for integer,token in enumerate(all_tokens)}


len(vocab.items())

for i, item in enumerate(list(vocab.items())[-5:]):
 #   print(item)

 tokenizer = SimpleTokenizerV1(vocab)

text1 = "Hello, do you like tea?"
text2 = "In the sunlit terraces of the palace."

text = " <|endoftext|> ".join((text1, text2))
tokenizer.encode(text)

print(text)