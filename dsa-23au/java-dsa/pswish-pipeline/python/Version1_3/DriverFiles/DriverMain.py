# Main method for the DriverData program
# This logic requires python 3.10

import DriverModes
from DriverData2 import DriverToDriveData
import Average
import logging
import sys

logger = logging.Logger

driver_data = DriverToDriveData()
driver_data.run_mode()
driver = driver_data.DataStructure
debug = False  # set to True to disable replay
ReplayCounter = 1

def main():
    exit = False
    try:
        user_input = (input("""\nPlease select a mode of operation: \
                            \n 1. Enter 1 to view available keys. \
                            \n 2. Enter 2 to view all available data in readable format. \
                            \n 3. Enter 3 to view a sample calculation. \
                            \n 4. Enter 4 for single data viewing by key. \
                            \n 5. Enter 5 to search the data for a value.  \
                            \n 6. Enter 6 to view total combined miles driven for both drivers in all data and cost. \
                            \n 7. Enter 7 view total combined time driven. \
                            \n 8. Enter 8 to view a range of n to k sorted example miles data. \
                            \n 9. Enter 9 to run the numbers \
                            \n 10. Enter 10 to quit. \
                            \n\n Please type your selection and push enter:  """))

        Modes = DriverModes
        
        match int(user_input):   
            case 1:
                Modes.print_keys(driver_data)
            case 2:
                Modes.pretty_print(driver)
            case 3:
                Modes.sample_calculation(driver_data, driver)
            case 4:
                Modes.search_by_key_and_value(driver_data, driver)
            case 5:
                Modes.search_by_value(driver)
            case 6:
                Modes.total_miles(driver_data, driver)
            case 7:
                Modes.total_time(driver_data, driver)
            case 8:
                Modes.calculate_distances(driver_data)
            case 9:
                Average.averager(driver)
            case 10:
                print("Exiting ...")
                exit = True

    except:
        print("No selection detected...")
    finally:
        global ReplayCounter
        if not debug:
            if ReplayCounter >= 10 or exit is True:
                print("You have selected option 10 or reached the max retries for this program, 10. End of program...")
        
            else: 
                replay = input("Push enter to run another operation or type 'exit' to quit: ")

                if replay == "exit".lower():  # replay the program main loop or type exit to quit
                    print("End of program...")
                    sys.exit()
                else: 
                    print("Replay counter", ReplayCounter, "out of 10")
                    ReplayCounter += 1
                    main()

if __name__ == main():
    main()