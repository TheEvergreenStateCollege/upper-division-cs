from PIL import Image 
import numpy as np

from loader import load_all_training_images, load_all_training_labels, load_all_test_images, load_all_test_labels

#############################################################################
## LOAD TRAINING IMAGES AND LABELS
# shape is (60000, 784, 1)
(original_training_images, width, height) = load_all_training_images()
print(f"Training image shape {original_training_images.shape}")
# save_single_image(original_training_images[127], width, height)

# shape is (60000, 10)
training_labels = load_all_training_labels()
print(f"Training labels shape {training_labels.shape}")
# load_training_label(127)

# shape is (60000, ((784, 1), 10))
# 60000 2-tuples, of (x,y) image,label training pair
original_training_data = list(zip(original_training_images, training_labels))

# shape is (10000, ((784, 1), 10))
#validation_data = original_training_data[50000:60000]
#print(f"Validation data shape {validation_data.shape}")

# Use all 60000 of original data for training for now
training_data = original_training_data

# shape is (10000, 784, 1)
(test_images, width, height) = load_all_test_images()
# shape is (10000, 10)
test_labels = load_all_test_labels()
# shape is (10000, ((784, 1), 10))
# 10000 2-tuples, of (x,y) image,label training pair
test_data = list(zip(test_images, test_labels))

from network import Network

nn = Network([784, 100, 10])
nn.SGD(
    training_data=training_data,
    epochs=10,
    mini_batch_size=1,
    eta=0.2,
    test_data=test_data
)

nn.saveToPBJSON("model.pbjson")
# When this stops, you'll have a model with ~96% success rate (4% error)

# Validate here with validate_data