from dataloader import create_dataloader_v1
from importlib.metadata import version

import importlib
import tiktoken
import torch

#print("PyTorch version:", torch.__version__)
#print("tiktoken version:", importlib.metadata.version("tiktoken"))

raw_text = "" 
with open("../data/dracula.txt", "r") as f:
    raw_text = f.read()

print(f"Total number of characters: {len(raw_text)}")
print(raw_text[:100])

# Output 1 -
# tokenizer = tiktoken.get_encoding("gpt2")

# # print integers
# text = "Hello, do you like tea? <|endoftext|> In the sunlit terraces of someunknownPlace."
# integers = tokenizer.encode(text, allowed_special={"<|endoftext|>"})
# print(integers)

# # print decoded sentence
# strings = tokenizer.decode(integers)
# print(strings)

# Output 2 -
# enc_sample = enc_text[50:]

# context_size = 4

# x = enc_sample[:context_size]
# y = enc_sample[1:context_size+1]

# print(f"x: {x}")
# print(f"y:      {y}")

# for i in range(1, context_size+1):
#     context = enc_sample[:i]
#     desired = enc_sample[i]

#     print(context, "---->", desired)

# for i in range(1, context_size+1):
#     context = enc_sample[:i]
#     desired = enc_sample[i]

#     print(tokenizer.decode(context), "---->", tokenizer.decode([desired]))

# Output 3 -
dataloader = create_dataloader_v1(raw_text, batch_size=1, max_length=4, stride=1, shuffle=False)

data_iter = iter(dataloader)
first_batch = next(data_iter)
print(first_batch)

second_batch = next(data_iter)
print(second_batch)

# Output 4 -
# dataloader = create_dataloader_v1(raw_text, batch_size=8, max_length=4, stride=4, shuffle=False)

# data_iter = iter(dataloader)
# inputs, targets = next(data_iter)
# print("Inputs:\n", inputs)
# print("\nTargets:\n", targets)

# Output 5 -
# input_ids = torch.tensor([2, 3, 5, 1])

# vocab_size = 6
# output_dim = 3

# torch.manual_seed(123)
# embedding_layer = torch.nn.Embedding(vocab_size, output_dim)

# print(embedding_layer.weight)

# print(embedding_layer(torch.tensor([3])))

# print(embedding_layer(input_ids))
