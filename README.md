# Packer-Unpacker

This project is used to perform process monitoring activity.
By using this project we can fetch data from all files and merge it into one file. As well
as we can also extract all packed file whenever required.

User Interface :
• Graphical User Interface

Technology used :
• Java Programming

Features provided by File Packer-Unpacker
• This project is divided into two parts as Packing and Unpacking.

Packing Activity :
• In case of Packing activity we accept directory name and file name from user.
• We have to create new regular file as the name specified by the user.
• Now open the directory and traverse each file from that directory. In newly created file
  write Metadata as header and actual file data in sequence.
• After packing display packing report.

UnPacking Activity :
• In case of UnPacking activity we accept packed file name from user.
• Open the packed file in read mode and perform below activity as
• Read header
• From the name specified in header create new file.
• Write data into newly created file from packed file.
• Repeat all above steps till we reached at end of the file unpacked file.
• After unpacking display unpacking report.
Main purpose of this project is to merge large amount of files into one file by avoiding
memory wastage.

