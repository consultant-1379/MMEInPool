#
# This script is called after package software has been installed and should perform solution-set deployment actions
#

echo "Performing solution set deployment from /opt/ericsson/mmepool"

source /opt/ericsson/mmepool/shared_solutionset_data.sh

# register all eps services

for srv in ${eps_services[@]:0}
do
	echo "Registering service $srv"
	cp /opt/ericsson/mmepool/$srv /etc/init.d/
	chkconfig --add $srv
	echo "Successfully registered service $srv"
done

# copy all flow descriptor files

for (( i=1; i<${#eps_services[@]}; i++ ))
do
	target_folder=${ROOT_EPS_DEPLOY_FOLDER}/${eps_services[i]}
	if [ ! -d $target_folder ]; then
		echo "Creating folder $target_folder"
		mkdir -p $target_folder
		chown $eps_user_name:$eps_user_name $target_folder
	fi
	# copy all xml files starting with service name
    for f in ${flow_files[i]}
    	do
    		flow_file_name=/opt/ericsson/mmepool/mmepool-flows/${f}
            echo "Copying $flow_file_name to $target_folder"
    		cp $flow_file_name $target_folder
    		chmod -R 544 $target_folder
    	done
done

# copy all other resources

eps_lib_dir=$EPS_HOME/ext-lib/

echo "copying jar files to $eps_lib_dir"
for jar in $jar_file_list
do
	echo "copying $jar into $eps_lib_dir"
	cp /opt/ericsson/mmepool/$jar $eps_lib_dir
	chown $eps_user_name:$eps_user_name $eps_lib_dir/$jar
	echo "changed permissions for $eps_lib_dir/$jar"
done

# create all required folders

cell_setup_dir="/northbound/ericsson/files/call_setup"

if [ ! -d cell_setup_dir ]; then
	echo "creating $cell_setup_dir"
	mkdir -p $cell_setup_dir
	chown $eps_user_name:$eps_user_name $cell_setup_dir
fi