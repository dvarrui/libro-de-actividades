Gem::Specification.new do |s|
  s.name = %q{extensions}
  s.version = "0.6.0"
  s.date = %q{2004-12-09}
  s.summary = %q{'extensions' is a set of extensions to Ruby's built-in classes.  It gathers common idioms, useful additions, and aliases, complete with unit testing and documentation, so they are suitable for production code.}
  s.email = %q{gsinclair@soyabean.com.au}
  s.homepage = %q{http://extensions.rubyforge.org}
  s.rubyforge_project = %q{extensions}
  s.description = %q{'extensions' is a set of extensions to Ruby's built-in classes.  It gathers common idioms, useful additions, and aliases, complete with unit testing and documentation, so they are suitable for production code. See http://extensions.rubyforge.org for full documentation.}
  s.default_executable = %q{rbxtm}
  s.has_rdoc = %q{true}
  s.files = ["ChangeLog", "HISTORY", "Rakefile", "README", "README.1st", "VERSION", "install-doc.rb", "install.rb", "install.sh", "bin/rbxtm", "lib/extensions", "lib/extensions/all.rb", "lib/extensions/array.rb", "lib/extensions/binding.rb", "lib/extensions/class.rb", "lib/extensions/continuation.rb", "lib/extensions/enumerable.rb", "lib/extensions/hash.rb", "lib/extensions/io.rb", "lib/extensions/kernel.rb", "lib/extensions/module.rb", "lib/extensions/numeric.rb", "lib/extensions/object.rb", "lib/extensions/ostruct.rb", "lib/extensions/string.rb", "lib/extensions/symbol.rb", "lib/extensions/_base.rb", "lib/extensions/_template.rb", "./test/data", "./test/tc_array.rb", "./test/tc_binding.rb", "./test/tc_class.rb", "./test/tc_continuation.rb", "./test/tc_enumerable.rb", "./test/tc_hash.rb", "./test/tc_io.rb", "./test/tc_kernel.rb", "./test/tc_module.rb", "./test/tc_numeric.rb", "./test/tc_object.rb", "./test/tc_ostruct.rb", "./test/tc_string.rb", "./test/tc_symbol.rb", "./test/TEST.rb", "./test/data/kernel_test", "./test/data/kernel_test/global_var_1.rb", "./test/data/kernel_test/global_var_2.rb", "etc/checklist", "etc/website", "etc/website/index.html", "etc/website/upload.sh", "test/tc_array.rb", "test/tc_binding.rb", "test/tc_class.rb", "test/tc_continuation.rb", "test/tc_enumerable.rb", "test/tc_hash.rb", "test/tc_io.rb", "test/tc_kernel.rb", "test/tc_module.rb", "test/tc_numeric.rb", "test/tc_object.rb", "test/tc_ostruct.rb", "test/tc_string.rb", "test/tc_symbol.rb"]
  s.test_files = ["test/tc_array.rb", "test/tc_binding.rb", "test/tc_class.rb", "test/tc_continuation.rb", "test/tc_enumerable.rb", "test/tc_hash.rb", "test/tc_io.rb", "test/tc_kernel.rb", "test/tc_module.rb", "test/tc_numeric.rb", "test/tc_object.rb", "test/tc_ostruct.rb", "test/tc_string.rb", "test/tc_symbol.rb"]
  s.rdoc_options = ["--title", "Ruby/Extensions API Documentation", "--main", "README", "--inline-source"]
  s.extra_rdoc_files = ["README"]
  s.executables = ["rbxtm"]
  s.requirements = ["Ruby 1.8 (or 'ruby_shim' from RAA)"]
end
