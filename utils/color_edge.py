import cv2
import numpy as np
from matplotlib import pyplot as plt

img = cv2.imread("images\\img01.png")
edges = cv2.Canny(img,100,200)

dist_edges = np.zeros(edges.shape, dtype=np.float64)
dist_edges[edges==0] = 1
dist_edges[edges==255] = 0

h, w = dist_edges.shape
min_dist = 1
max_dist = 0
for y in range(len(dist_edges)):
    for x in range(len(dist_edges[y])):
        if dist_edges[y,x] == 0:
            dist_edges[y,x] = (x+y)/(h+w)
            min_dist = min(dist_edges[y, x], min_dist)
            max_dist = max(dist_edges[y, x], max_dist)

dist_edges = (dist_edges - min_dist) / (max_dist - min_dist)
rgb_edges = np.zeros(edges.shape, dtype=np.float64)
cmap = plt.get_cmap('hsv')
rgb_edges = cmap(dist_edges)[:,:,:3]
rgb_edges[edges==0] = 1

rgb_edges[rgb_edges>1] = 1
rgb_edges[rgb_edges<0] = 0
rgb_edges = (rgb_edges*255).astype('uint8')

cv2.imwrite("images\\output_mm01.png", rgb_edges)

plt.subplot(121),plt.imshow(img,cmap = 'gray')
plt.title('Original Image'), plt.xticks([]), plt.yticks([])
plt.subplot(122),plt.imshow(rgb_edges,cmap = 'gray')
plt.title('Edge Image'), plt.xticks([]), plt.yticks([])
plt.show()
