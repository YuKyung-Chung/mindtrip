import { Progress } from "@nextui-org/react";

function MyProgress() {
  return (
    <Progress
      size="sm"
      radius="sm"
      classNames={{
        base: "max-w-md",
        track: "drop-shadow-md border border-default min-h-8",
        indicator: "bg-gradient-to-r from-pink-500 to-yellow-500",
        label: "tracking-wider font-medium text-default-600",
        value: "text-foreground/60",
        
      }}
      label="달성한 미션"
      value={80}
      showValueLabel={true}
    />
  );
}

export default MyProgress