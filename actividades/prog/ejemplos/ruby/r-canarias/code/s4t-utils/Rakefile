$started_from_rakefile=true

PACKAGE_ROOT = Dir.pwd
$:.unshift("#{PACKAGE_ROOT}/lib")
require 's4t-utils'
include S4tUtils

MyFileSystemName='s4t-utils'  # No ".rb" extension.
MyModuleName='S4tUtils'
MyRdocFiles = FileList.new("lib/#{MyFileSystemName}.rb",
                           "lib/#{MyFileSystemName}/*.rb").exclude('**/third-party/**')
			   
require 's4t-utils/rakefile-common'
