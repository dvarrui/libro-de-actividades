#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
# This is the part of a Rakefile that is common to all those that
# follow the Scripting for Testers format.

require 'pathname'

desc 'The default is "rake test".'
task 'default' => 'test'

desc "Run fast and slow tests."
task 'test' => ['fast', 'slow']

task 'tests' => 'test'
task 'alltests' => 'tests'
task 'all-tests' => 'alltests'

$test_search_root=Pathname.new(PACKAGE_ROOT)+'test'
$rakefile_local=''
ENV['S4T_ONLY_PENDING_TESTS'] ||= 'false'

desc "Limit scope of testing to current directory."
task 'local' do 
  $test_search_root=Pathname.new(Rake.original_dir)
  $rakefile_local='local'
end

desc "Run only pending tests. (They're normally ignored.)"
task 'pending' do 
  ENV['S4T_ONLY_PENDING_TESTS'] = 'true'
end

desc "Run fast tests."
task 'fast' do
  run_particular_tests($test_search_root, 'fast')
end

desc "Run slow tests."
task 'slow' do
  run_particular_tests($test_search_root, 'slow')
end

desc "Increment the most minor part of the version number."
task 'increment-version'  do
  increment_version("lib/#{MyFileSystemName}/version.rb")
end

desc 'Commit entire tree, popping up $EDITOR for comments.'
task 'commit' => 'all-tests-in-a-subprocess' do
  if SvnFileTransfer.under_svn_control?("./Rakefile")
    puts `svn commit`
  else
    puts "-> note: not under version control"
  end
end

desc 'Update peer packages that use this one.'
task 'update-peers'

Dir.glob("../*/lib/*/third-party/#{MyFileSystemName}").each do | peer | 
  dest = Pathname.new(peer).parent
  taskname = update_peer_subtask_name(dest.parent.basename)
  task 'update-peers' => taskname
  task taskname do
    SvnFileTransfer.transfer_dir(Pathname.new('lib'), dest, :keep_others)
  end
end

desc "Install into peer directory, named with peer=NAME."
task 'install-into' do
  peer = ENV['peer']
  third_party = Pathname.new("../#{peer}/lib/#{peer}/third-party")
  if File.exist?(third_party)
    SvnFileTransfer.transfer_dir(Pathname.new('lib'), third_party,
                                 :keep_others)
  else
    raise "No such peer: '#{peer}'"
  end
end


desc "Test, increment version, commit, and update peers."
task 'move-on' => [
  'confirm', 'all-tests-in-a-subprocess',  'increment-version',
  'commit',  'update-peers'
]

desc "Install (with setup.rb) after running tests."
task 'install' => 'all-tests-in-a-subprocess' do 
  system('ruby setup.rb all')
end

task 'confirm' do
  exit(1) unless ask('y', "Continue?") == 'y'
end
     
task 'all-tests-in-a-subprocess' do
  subprocess('alltests')
end

require 'rake/rdoctask'
Rake::RDocTask.new do |rd|
  rd.main = MyModuleName
  rd.rdoc_dir = "doc/html"
  rd.rdoc_files = MyRdocFiles
end
