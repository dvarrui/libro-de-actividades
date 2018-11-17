require 'rake'
require 'rubygems'
require 'rake/rdoctask'
require 'rake/packagetask'
require 'rake/gempackagetask'
require 'rake/testtask'
 
  # Return the current version (e.g. '0.3.1') from the file VERSION.
def current_version
  version = File.read("VERSION").strip
  fail "Invalid version information" unless version =~ /\./
  version
end

PACKAGE_FILES = FileList['[A-Z]*', 'install*', 'bin/**/*', 'lib/**/*', './test/**/*', 'etc/**/*']
EXT_VERSION = current_version()

#
# ------------------------------------------------------------------------ Gem specification
#

spec = Gem::Specification.new do |s|
  s.name = 'extensions'
  s.version = EXT_VERSION
  s.platform = nil
  s.requirements << "Ruby 1.8 (or 'ruby_shim' from RAA)"
  #
  s.files = PACKAGE_FILES.to_a
  #
  s.summary =<<EOF
'extensions' is a set of extensions to Ruby's built-in classes.  It gathers
common idioms, useful additions, and aliases, complete with unit testing and
documentation, so they are suitable for production code.
EOF
  s.description = s.summary + <<EOF

See http://extensions.rubyforge.org for full documentation.
EOF
  #
  s.require_path = 'lib'
  s.autorequire = nil
  s.bindir = 'bin'
  s.executables = ['rbxtm']
  s.default_executable = 'rbxtm'
  #
  s.has_rdoc = 'true'                         # TODO: RDoc arguments?
  s.rdoc_options << '--title' << 'Ruby/Extensions API Documentation' <<
                    '--main' << 'README' << '--inline-source'
  s.extra_rdoc_files = ['README']
  s.test_files = FileList['test/tc_*.rb'].to_a
  #
  s.author = 'Gavin Sinclair'
  s.email = 'gsinclair@soyabean.com.au'
  s.homepage = 'http://extensions.rubyforge.org'
  s.rubyforge_project = 'extensions'
end

#
# ------------------------------------------------------------------------ Tasks
#

directory 'build/packages'
directory 'build/rdoc'

  # RDoc generation (:rdoc, :rerdoc)
Rake::RDocTask.new(:rdoc) do |r|
  r.rdoc_dir = "build/rdoc"
  r.title = "Ruby/Extensions API Documentation"
  r.main = "README"
  r.options << "--inline-source"
  r.rdoc_files = FileList["lib/**/*.rb", "README"]
end

  # Gem and TGZ packaging (:package)
Rake::GemPackageTask.new(spec) do |pkg|
  pkg.package_dir = "build/packages"
  pkg.need_tar = true
end

  # Install the gem.
task :install => :repackage do
  file = Dir['build/packages/*.gem'].last or fail
  sh "gem install #{file}"
end

  # Upload website (:website)
desc 'Upload RDoc information to RubyForge'
task :website => :rerdoc do
  # TODO: write me.
end

desc 'Run the unit tests'
task :test do
  exec %{ruby 'test/TEST.rb' #{ARGV[1..-1].join(' ')}}
end

desc 'List all extensions methods'
task :methods do
  ruby '-Ilib bin/rbxtm'
end

# vim: ft=ruby
