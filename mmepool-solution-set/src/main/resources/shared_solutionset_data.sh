#
# This script is shared between shared_solution deploy and undeploy scripts
#
EPS_HOME=/opt/ericsson/eps/eps

eps_user_name=epsadmin

eps_services=""


for i in {1..2}
do
   eps_services[i]="mmepool_eps0$i"
done



echo "eps services are ${eps_services[@]:0} "

flow_files[1]="mmepool_call_setup.xml"
flow_files[2]="mmepool_call_setup.xml"


echo "there are ${#flow_files[@]} xml flows definitions"

ROOT_EPS_DEPLOY_FOLDER=/var/ericsson/eps/flows

jar_file_list="mmepool-outputAdapter-${project.version}.jar mmepool-eventHandler-${project.version}.jar mmepool-correlations-${project.version}.jar mmepool-metadata-${project.version}.jar xstream-base-${version.xstream}.jar xstream-correlation-${version.xstream}.jar xstream-metadata-${version.xstream}.jar wwecds-core-api-${version.epc}.jar wwecds-outputadaptor-${version.epc}.jar guava-${version.guava}.jar joda-time-${version.joda.time}.jar"
