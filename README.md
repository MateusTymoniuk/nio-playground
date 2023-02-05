# nio-playground
This repository was meant to be just for playing with Java.nio buffers and channels

It cointains 2 classes, Read and Write.

The idea was to create to channels torwards one file, and then write and read "at the same time".

Also, using a SeekableByteChannel to be able to walk the bytes of the file in a way that we keep track of where we have been already.
