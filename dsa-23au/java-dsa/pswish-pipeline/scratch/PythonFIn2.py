# Written by Paul Swisher @pswish, November 2023
# This function takes two csv files and puts them into a list : nested dictionary 
# Several functions are included to calculate different outputs 

import csv
import pprint

class DriverToDriveData:

    def __init__(cls):  # declares important variables
        cls.Line = []
        cls.dates = []
        cls.driver = []
        cls.driverAndDates = {}
        cls.filepath1 = "dsa-23au/java-dsa/pswish-pipeline/scratch/DataSet_DSAau_pswish.csv"
        cls.filepath2 = "dsa-23au/java-dsa/pswish-pipeline/scratch/Time_Driving_Spreadsheet.csv"

    def getCSVfile(cls):  # Opens both csv files and adds them to a variable 
        try:
            with open (cls.filepath1, "r") as cls.file:  # sets each in file line to a list, maybe refactor this
                cls.Line = [line for line in cls.file]  
            with open (cls.filepath2, "r") as cls.file2: # sets each in file2 line to a list, maybe refactor this
                cls.Line2 = [line2 for line2 in cls.file2]
        except Exception as e:
            print("fault at getCSVfilewith error ", e)


    def addCSVtoLists(cls):  # TODO change this to be create first set of driver keys
        lineLength = cls.Line.__len__()
        
        cls.driverKeys = []
        cls.driver1keys = []
        i = 1
        while i < lineLength:
            cls.Linelist = cls.Line[i].split(",")

            # Date, time and driver constructed into a unique key
            driver1Key = (f"{cls.Linelist[1]}_{cls.Linelist[0]}_{cls.Linelist[6].replace('P', 'Paul')}")
            
            cls.driverKeys.append(driver1Key)  # main driver key list
            cls.driver1keys.append(driver1Key) # driver1 key list
            i+=1


    def addCSVtoList2(cls): # TODO Change this to be create driver 2 keys

        lineLength2 = cls.Line2.__len__()

        cls.driver2Keys = []
        j = 1

        while j < lineLength2:
            cls.Line2list = cls.Line2[j].split(",")
            driver2Key = (f"{cls.Line2list[1]}_{cls.Line2list[0]}_{cls.Line2list[6]}")
            cls.driver2Keys.append(driver2Key)
            j+=1

    def addListitemsToDict(cls):  # Add the data for driver 1 to a dictionary
        cls.addCSVtoLists()
        dict_1 = {}
        
        with open (cls.filepath1, "r") as f:  # Open csv file with a class object
            dict_reader = csv.DictReader(f)
            i = 0
            lines = []

                # add lines from dic_reader to a list for scripting
            for line in dict_reader:
                lines.append(line)

                # construct the data structure 
            for i in range(len(cls.driverKeys)):
                key = cls.driverKeys[i]
                value = lines[i]
                dict_1[key] = value
                cls.DataStucture = dict_1  # set class wide data object

    def addListitemsToDict2(cls): # Add the data for driver 2 to a dictionary
        cls.addCSVtoList2()
        dict_2 = {}
        
        with open (cls.filepath2, "r") as f:  # Open csv file with a class object
            dict_reader = csv.DictReader(f)
            i = 0
            lines = []

                # add lines from dic_reader to a list for scripting
            for line in dict_reader:
                lines.append(line)

                # construct the data structure 
            for i in range(len(cls.driver2Keys)):
                key = cls.driver2Keys[i]
                value = lines[i]
                dict_2[key] = value
                cls.DataStucture.update(dict_2)  # set class wide data object
        
        for key2 in cls.driver2Keys:  # adds driver 2 keys to main driverkeys list
            cls.driverKeys.append(key2)

    def print(cls):  # prints out the nested dictionary in an easy to read format
        pp = pprint.PrettyPrinter(depth=4)
        pp.pprint(cls.DataStucture)
        pp.pprint(cls.driverKeys)

        # Accessing the info
        # with the key, and vlaue name, you can get the value of value
        # ["9/29/2023_1807_Nathan"]["Distance"])

    def operationMode(cls, sel, *kwargs) -> int:  # Data manipulation operation
        try:
            cls.sorted_keys = sorted(cls.driverKeys)
            cls.driver1sorted = sorted(cls.driver1keys)
            cls.driver2sorted = sorted(cls.driver2Keys)
            selection = int(sel)

            if selection == 1:  # get data by key and value entry
                key = input("Please enter a key, example '9/29/2023_1807_Nathan': ")
                value = input("please enter a value name, example 'Distance': ")
                print("For key :", key)
                print("The value is: ", value)
                compile = cls.DataStucture[key][value]
                print(compile)

            elif selection == 2:  # Prints all keys sorted and unsorted 
                print("Being Unsorted:")
                for keys in cls.driverKeys:
                    print(keys) 
                print("\n--*-- End unsorted --*\n")
                
                print("Begin sorted:\n")
                i = 0
                for s_key in cls.sorted_keys:
                    print(i, s_key)
                    i += 1
                print("\n--*-- End Sorted --*--\n")
            
            elif selection == 3:  # prints example calculation for miles * cost per mile
                x = int((cls.DataStucture['10/19/2023_0850_Paul']['Distance']).split()[0]) 
                y = int((cls.DataStucture['10/19/2023_1700_Paul']['Distance']).split()[0])
                num = ((x + y)*.16)  # miles * cost per mile
                formatted_num = f'{num:.2f}'
                
                print('Distance in miles: ', x + y)
                print("Total cost (miles * cost per mile($0.16)): $", formatted_num)

            elif selection == 4:  # pretty prints all data
                cls.print()

            elif selection == 5:  # prints out total miles driven for both drivers
                total_miles = 0
                for key in cls.sorted_keys:
                    compile = cls.DataStucture[key]["Distance"]

                    x = int(compile.split()[0])
                    # print(x)
                    total_miles += x
                print("Total combined miles: ", total_miles)

            elif selection == 6:  # Choose 1, 2, both drivers for start and end data on distances
                total_miles = 0
                driver = input("For selection: enter 1 or 2, for driver 1, driver 2 or any other number for both drivers: ")
                try: 
                    inputDriver = int(driver)
                except Exception as e:
                    print ("error in input", e)
                if inputDriver == 1:
                    print(cls.driver1sorted)  # Prints out Driver1 keys to pick a range manually

                    inputStart = input("Enter a start: ")
                    inputStop =  input("Enter an end: ")
                    start = cls.driver1sorted.index(inputStart)
                    end = cls.driver1sorted.index(inputStop)
                    select = cls.driver1sorted[start:end]
    
                elif inputDriver == 2:  # Prints out Driver2 keys to pick a range manually
                    print(cls.driver2sorted)
                    
                    inputStart = input("Enter a start: ")
                    inputStop =  input("Enter an end: ")
                    start = cls.driver2sorted.index(inputStart)
                    end = cls.driver2sorted.index(inputStop)
                    select = cls.driver2sorted[start:end]
                else:
                    inputStart = input("Enter a start: ")
                    inputStop =  input("Enter an end: ")
                    start = cls.sorted_keys.index(inputStart)
                    end = cls.sorted_keys.index(inputStop)
                    select = cls.sorted_keys[start:end]

                for i in select:  # Takes the selection data from above and calcualtes a hardcoded distance
                    compile = cls.DataStucture[i]["Distance"]
                    x = int(compile.split()[0])
                    total_miles += x
                    print(i, compile)
                print("Total combined miles: ", total_miles)

            else: 
                print("Nothing to do")

        except Exception as e:
            print("Error in operation mode: ", e)
        finally:
            replay = input("Push enter to run another operation or type 'exit' to quit: ")

            if replay == "exit".lower():  # replay the program main loop or type exit to quit
                print("End of program...")
            else: 
                main()


        #   TODO      
        #   Maybe a filterd date range for me from date1-date5, total miles (done_)
        #   Maybe a date range of 30 days, total miles (done by key)
        #   Maybe with ton of those data points... (forget what this was for)  
        #   XXX Add a print keys with index to the individual driver and then make the selection take the number from the printed list for application
        #   TODO add functions for each value than can be calculated upon (Miles, time spent driving)
        
        #   TODO v1.1 break the large function up into separate files 
        #   cls.DataStucture[key][value])  # the nice way for accessing data

        # Example:
        #  '10/9/2023_1830_Paul': {'Date': '10/9/2023',
        #              'Dest': 'Millersylvania',
        #              'Detour enroute': 'no',
        #              'Distance': '11 miles',
        #              'Driver': 'P',
        #              'Elapsed': '22 mins',
        #              'Orig': 'Evergreen',
        #              'Time': '1830'},

def main():
    DTDD = DriverToDriveData()
    DTDD.getCSVfile()
    DTDD.addListitemsToDict()
    DTDD.addListitemsToDict2()
    DTDD.operationMode(input("Please select a mode of operation: \
                            \n 1. Enter 1 for data viewing by key, \
                            \n 2. Enter 2 to view available keys, \
                            \n 3. Enter 3 to view a sample calculation \
                            \n 4. Enter 4 to view all avialable data in readable format. \
                            \n 5. Enter 5 to view total combined miles driven. \
                            \n 6. Enter 6 to view a range of n to k sorted example miles data \
                            \n\n Please type your selection and push enter:  "))

if __name__ == "__main__":
    main()